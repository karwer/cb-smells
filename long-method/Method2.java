public float getAverageCenterFrequency(int i)
  {
    if ( whichAverage == LINAVG )
    {
      // an average represents a certain number of bands in the spectrum
      int avgWidth = (int) spectrum.length / averages.length;
      // the "center" bin of the average, this is fudgy.
      int centerBinIndex = i*avgWidth + avgWidth/2;
      return indexToFreq(centerBinIndex);
            
    }
    else if ( whichAverage == LOGAVG )
    {
      // which "octave" is this index in?
      int octave = i / avgPerOctave;
      // which band within that octave is this?
      int offset = i % avgPerOctave;
      float lowFreq, hiFreq, freqStep;
      // figure out the low frequency for this octave
      if (octave == 0)
      {
        lowFreq = 0;
      }
      else
      {
        lowFreq = (sampleRate / 2) / (float) Math.pow(2, octaves - octave);
      }
      // and the high frequency for this octave
      hiFreq = (sampleRate / 2) / (float) Math.pow(2, octaves - octave - 1);
      // each average band within the octave will be this big
      freqStep = (hiFreq - lowFreq) / avgPerOctave;
      // figure out the low frequency of the band we care about
      float f = lowFreq + offset*freqStep;
      // the center of the band will be the low plus half the width
      return f + freqStep/2;
    }
    
    return 0;
  }