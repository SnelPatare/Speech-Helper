#!/usr/bin/env python
# coding: utf-8

# In[2]:
import keras
import librosa
import numpy as np
import sys
import tensorflow
import os


# In[3]:


#from google.colab import drive
#drive.mount('/content/drive')


# In[4]:


class LivePredictions:
    """
    Main class of the application.
    """

    def __init__(self, file_path, model_path):
        """
        Init method is used to initialize the main parameters.
        """
        self.file_path  = file_path
        self.model_path = model_path
        self.loaded_model = keras.models.load_model(self.model_path)

    def make_predictions(self):
        """
        Method to process the files and create your features.
        """
        data, sampling_rate = librosa.load(self.file_path)
        mfccs = np.mean(librosa.feature.mfcc(y=data, sr=sampling_rate, n_mfcc=40).T, axis=0)
        x = np.expand_dims(mfccs, axis=1)
        x = np.expand_dims(x, axis=0)
        #predictions = self.loaded_model.predict_classes(x)
        predict_x=self.loaded_model.predict(x) 
        classes_x=np.argmax(predict_x,axis=1)
        print( "Prediction is", " ", self.convert_class_to_emotion(classes_x))
        '''
        ## If file exists, delete it ##
        if os.path.isfile(self.file_path):
            os.close(self.file_path)
            os.remove(self.file_path)
            print("File deleted")
        '''

    @staticmethod
    def convert_class_to_emotion(pred):
        """
        Method to convert the predictions (int) into human readable strings.
        """
        
        label_conversion = {'0': 'neutral',
                            '1': 'calm',
                            '2': 'happy',
                            '3': 'sad',
                            '4': 'angry',
                            '5': 'fearful',
                            '6': 'disgust',
                            '7': 'surprised'}

        for key, value in label_conversion.items():
            if int(key) == pred:
                label = value
        return label

if __name__ == '__main__':
    if( len(sys.argv) != 3):
        print(" Pass correct Arguments : {liveAudio.py} {model_path} {file_path}");
    model_path = sys.argv[1]
    file_path  = sys.argv[2]
    print ('Sentiment analysis for live audio:')
    live_prediction = LivePredictions(file_path, model_path)
    #live_prediction.loaded_model.summary()
    live_prediction.make_predictions()
    del live_prediction
# In[ ]:




