
import glob, os, sys
import nltk; import re; from nltk import word_tokenize; from nltk.corpus import conll2000
os.chdir("...")  #from locan folder 
for file in glob.glob("*.txt"):
        raw = open(file,'rU').read() #'r' means to open the file for reading (the default), and 'U' stands for "Universal", which lets us ignore the different conventions used for marking newlines.
        newfilename = file + 'processed.txt'
        newfile = open(newfilename, 'w')
        def ie_preprocess(raw):
                sentences = nltk.sent_tokenize(raw)
                sentences = [nltk.word_tokenize(sent) for sent in sentences]
                sentences = [nltk.pos_tag(sent) for sent in sentences]
                return (sentences)
        # print(ie_preprocess(raw))
        sentence_list = ie_preprocess(raw) #list already
        # Natural Language Toolkit: code_unigram_chunker
        train_sents = conll2000.chunked_sents('train.txt', chunk_types = ['NP'])
        class UnigramChunker(nltk.ChunkParserI):
            def __init__(self, train_sents): # [_code-unigram-chunker-constructor]
                train_data = [[(t,c) for w,t,c in nltk.chunk.tree2conlltags(sent)]
                              for sent in train_sents]
                self.tagger = nltk.UnigramTagger(train_data) # [_code-unigram-chunker-buildit]

            def parse(self, sentence): # [_code-unigram-chunker-parse]
                pos_tags = [pos for (word,pos) in sentence]
                tagged_pos_tags = self.tagger.tag(pos_tags)
                chunktags = [chunktag for (pos, chunktag) in tagged_pos_tags]
                conlltags = [(word, pos, chunktag) for ((word,pos),chunktag)
                             in zip(sentence, chunktags)]
                return nltk.chunk.conlltags2tree(conlltags)
        unigram_chuncker = UnigramChunker(train_sents)
        for sent in sentence_list:
                tree = unigram_chuncker.parse(sent)  #(S(NP Admission/NN Date/NNP)  :/:(NP 20
                IOB_doc = nltk.chunk.tree2conllstr(tree)
                #print (IOB_doc)
                newfile.write(IOB_doc + '\n')
        newfile.close()

