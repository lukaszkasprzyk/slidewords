# slidewords
This is a very simple search engine. For given phrase it's looking for some results on database.
It tries to find best matches according to following algorithm.
* For input word splits whole phrase by white spaces
* For every subset of given input it creates "slide" as a part of whole phrase with particular words.
* For every single slide it looks into database for that phrase.
* Longer phrases fits better than short ones.

I.e 
Input phrase: "Mary gone"
It creates two slides: 1. one phrase: "Mary gone", 2. two phrases: "Mary", "Gone"
On database we have only "Mary" and "Mary gone".
Result should be "Mary gone" as best match.
