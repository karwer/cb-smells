public DoubleFFT_1D(long n)
    {
        if (n < 1) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.useLargeArrays = (CommonUtils.isUseLargeArrays() || 2 * n > LargeArray.getMaxSizeOf32bitArray());
        this.n = (int) n;
        this.nl = n;
        if (this.useLargeArrays == false) {
            if (!CommonUtils.isPowerOf2(n)) {
                if (CommonUtils.getReminder(n, factors) >= 211) {
                    plan = Plans.BLUESTEIN;
                    nBluestein = CommonUtils.nextPow2(this.n * 2 - 1);
                    bk1 = new double[2 * nBluestein];
                    bk2 = new double[2 * nBluestein];
                    this.ip = new int[2 + (int) ceil(2 + (1 << (int) (log(nBluestein + 0.5) / log(2)) / 2))];
                    this.w = new double[nBluestein];
                    int twon = 2 * nBluestein;
                    nw = twon >> 2;
                    CommonUtils.makewt(nw, ip, w);
                    nc = nBluestein >> 2;
                    CommonUtils.makect(nc, w, nw, ip);
                    bluesteini();
                } else {
                    plan = Plans.MIXED_RADIX;
                    wtable = new double[4 * this.n + 15];
                    wtable_r = new double[2 * this.n + 15];
                    cffti();
                    rffti();
                }
            } else {
                plan = Plans.SPLIT_RADIX;
                this.ip = new int[2 + (int) ceil(2 + (1 << (int) (log(n + 0.5) / log(2)) / 2))];
                this.w = new double[this.n];
                int twon = 2 * this.n;
                nw = twon >> 2;
                CommonUtils.makewt(nw, ip, w);
                nc = this.n >> 2;
                CommonUtils.makect(nc, w, nw, ip);
            }
        } else if (!CommonUtils.isPowerOf2(nl)) {
            if (CommonUtils.getReminder(nl, factors) >= 211) {
                plan = Plans.BLUESTEIN;
                nBluesteinl = CommonUtils.nextPow2(nl * 2 - 1);
                bk1l = new DoubleLargeArray(2l * nBluesteinl);
                bk2l = new DoubleLargeArray(2l * nBluesteinl);
                this.ipl = new LongLargeArray(2l + (long) ceil(2l + (1l << (long) (log(nBluesteinl + 0.5) / log(2.)) / 2)));
                this.wl = new DoubleLargeArray(nBluesteinl);
                long twon = 2 * nBluesteinl;
                nwl = twon >> 2l;
                CommonUtils.makewt(nwl, ipl, wl);
                ncl = nBluesteinl >> 2l;
                CommonUtils.makect(ncl, wl, nwl, ipl);
                bluesteinil();
            } else {
                plan = Plans.MIXED_RADIX;
                wtablel = new DoubleLargeArray(4 * nl + 15);
                wtable_rl = new DoubleLargeArray(2 * nl + 15);
                cfftil();
                rfftil();
            }
        } else {
            plan = Plans.SPLIT_RADIX;
            this.ipl = new LongLargeArray(2l + (long) ceil(2 + (1l << (long) (log(nl + 0.5) / log(2)) / 2)));
            this.wl = new DoubleLargeArray(nl);
            long twon = 2 * nl;
            nwl = twon >> 2l;
            CommonUtils.makewt(nwl, ipl, wl);
            ncl = nl >> 2l;
            CommonUtils.makect(ncl, wl, nwl, ipl);
        }
    }