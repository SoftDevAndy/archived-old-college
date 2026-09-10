# SoftDevAndy
# February 2016


def recursive(permuset, prefix, word):

    # From in class and numerous examples online, this recursive method
    # seems to be the de-facto way of generating permutaions efficiently

    # Sources
    # https://sites.google.com/site/learnjav/java/recursion
    # https://sites.google.com/site/learnjav/java/recursion/Anagrams.java?attredirects=0

    # Added a parameter for passing a set
    # It's a set because it ignores duplicates when
    # using words with repeating characters e.g test has two t's
    # but a word with no repeating characters would be something like bath

    i = 0
    if len(word) <= 1:
        permuset.add(prefix + word)
    else:
        while i < len(word):

            x = i + 1

            current = word[i:x]
            before = word[0:i]
            after = word[x:]
            recursive(permuset, prefix + current, before + after)
            i += 1


def get_permuations(given_word):
    permus = set()

    print("\nPreprocessing")

    recursive(permus, "", given_word)

    print("All Permutations generated: ", len(permus))

    return permus

# Simple file reader which adds every word in the file to a set


def import_dictionaryfile(file_name):

    wordlist = set()

    with open(file_name, 'r') as f: # Reading in the file
        for word in f:  # For every word in the file read in
            word = word.strip('\n')  # Strip the new line character from the word
            wordlist.add(word)  # Add the word to the set
    f.close()  # Close the file

    # Finally return the set of words

    return wordlist
