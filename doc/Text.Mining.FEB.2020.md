
### LSA/LSI vs LDA

* is LDA incremental ? 
    - I think so, based on AWS NTM video definitions?
* language independent?
* if LDA is an extension of LSA/LSI 

### terms

* latent representation: inferred variables
* generative process: "as the algo learns/goes"
    - in contrast with discriminative
* perplexity: 
    - measure models ability to describe documents
    - we want to tweak to optimize for perplexity 
* topic coherence:
    - measures model quality by similarity of 'top words' within topic 

### brainstorm

* unsupervised learning (for verbage) 
* use topic coherence to evaluate model
* use topic model visualization where necessary
* maybe REST api has API key (?) 
* 7 domains could be custom vocabulary ?

### from AWS NTM vid 

* NTM runs on Sagemaker ?
* sarcasm, double entendre
* NTM vs Comprehend 
    - NTM: custom vocabulary ... ?
    - NTM: regulatory hurdles
    - Comprehend: simpler 
* internals of NTM != LDA 

### from AWS LDA vid

* Sagemaker offers Amazon Algorithms, optimized for AWS at scale
* AaaS ... Sagemaker is machine learning platform 
* primary use-case: topic modeling   
* mickey mouse: Guassian mixture model 
    - 3 clusters (ears, head)
    - identify center of clusters and distribution (statistical model) 
    - typical model is K-means clustering 
        - probably doesn't have the double distribution aspect ? 
        - only identifies the center
* topic modeling
    - 1000s of documents
    - cluster them into topics  
    - unsupervised: LDA does not have the topics upfront 
    - topics have a set of words with probabilities 
    - topics don't have names ... ! 
* LDA
    - hyper-parameters 
    - alpha: avg freq that each topic within a doc occurs
        - high alpha will make more docs seem more similar 
    - beta: collection of k-topics where each topic is given a prob
            distribution over the vocabluary used in a given corpus
            ... also a "topic-word" distribution 
        - high beta will make more topics seem similar 
    - is not an algorithm but rather a distribution 

### LDA on YT

* how many topics/dimensions?   
* docs have features: distinct enough to be separate, yet similar enough to be grouped
* we label topics 
* LDA space is a simplex: used to compare docs 
* Jensen-Shannon Distance used to compare probability distributions 
* George Box, "All models are wrong, but some are useful."

### from Safari 1

* implies we want topic modeling 
* LSI/LSA and LDA: probabilistic statistical models 
    - they use LSI... keep an eye out for similarity query  
* makes a lot of sense after Gensim deep-dive
* not sure what 'predicting topics' is ?
* uses Gensim and Scikit-Learn 
* bag-of-words seems to be common start for feature engineering
* LSI
    - uses Gensim to extract topics 
* LDA
    - topic coherence might be used to check the system?
    - MALLET 

### from Safari 2

- very nice writing style 
- ch 6
- maybe topic modeling is just 'clustering for text similarity' ? 
    - clustering is deductive
        - put documents into piles/groups
    - topic modeling is inductve
- partitive clustering and hierarchical clustering
    - then Gensim! 
    - LDA as an alternative
- LDA
    - topic discovery technique
    - topic is a distribution over words
    - document invokes multiple topics 
    - 'Dirichlet prior' a way of measuring distributions over distributions 
    - tunable pipeline
        - extrapolate topics from unstructured data
        - method for storing best model for new, incoming data 
    - mentions pyLDAvis as well  
- LSA
    - simpler than LDA: finds groups of documents with the same words 
    - SVD is factorization of matrices 


### Resources

* Safari ... D Shankar Text Analytics with Python
* Safari ... R Bilbro, T Ojeda, Benjamin Bengfort ... Applied Text Analysis with Python  
* MALLET - http://mallet.cs.umass.edu/topics.php
* topic model viz - https://github.com/bmabey/pyLDAvis
* AWS Neural Topic Modeling - https://www.youtube.com/watch?v=eAMjEv7EABM
* AWS LDA - https://www.youtube.com/watch?v=NMDL8Atim1k
* LDA - https://www.youtube.com/watch?v=3mHy4OSyRf0

### Cloud

* Azure ML Studio (Classic) - https://docs.microsoft.com/en-us/azure/machine-learning/studio-module-reference/latent-dirichlet-allocation
* AWS NTM - see above
* AWS Comprehend - https://aws.amazon.com/comprehend/ 
* AWS Sagemaker - https://aws.amazon.com/sagemaker
