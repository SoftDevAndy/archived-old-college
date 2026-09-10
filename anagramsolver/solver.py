# SoftDevAndy
# February 2016

import preproc
import random
import collections
import time


def checkvalidconundrum(wordin):

    # Used the check if the 9 letter word is valid
    # Pass in a 9 letter word
    # Is uppercase
    # That contains no numbers
    # Contains at least 3 vowels and 4 consonants

    wordin = wordin.upper()

    vowels = list("AEIOU")
    consonants = list("BCDFGHJKLMNPQRSTVWXYZ")

    # Used this source for counting consonants and vowels
    # http://stackoverflow.com/questions/7736211/python-counting-the-amount-of-vowels-or-consonants-in-a-user-input-word

    number_of_consonants = sum(wordin.count(c) for c in consonants)
    number_of_vowels = sum(wordin.count(c) for c in vowels)

    wordin = wordin.strip()  # Strips the whitespace from both sides of the word

    if len(wordin) != 9:  # If the anagram given is 9 letters long
        return False
    if number_of_consonants < 4:  # If the anagram contains at least 4 consonants
        return False
    if number_of_vowels < 3:  # If the anagram contains at least 3 vowels
        return False
    if wordin.isalpha() is False:  # If all the letters are just alphabetical
        return False

    # If all the above checks are passed we have a valid possible conundrum

    return True


def dropwords(anagram, wordlist):

    # Takes in the possible anagram for example PINESSHAP
    # Takes in the list of dictionary words list

    # Next the anagram is parsed to a set e.g PINESSHAP to PINESHA, all duplicate letters are discarded
    # Then for each word in the words list you parse that to a set and subtract both sets from each other
    # e.g the word CAN - PINESHA leaves a left over of the letter C which is a total of 1 leftover letter (we look for none)
    # The letter C cannot be used to create an anagram this means we can discard the word Can and not add it to the new word list
    # An example of a word that would be added to the set is NIP - PINESHA leaves no left over letters (0)

    cleanwordlist = set()  # Set of words that meet the criteria
    letters = set(anagram)  # Creates a set of letters of the given anagram

    for word in wordlist: # For each word in the wordlist
        if len(list(set(word) - set(letters))) is 0:  # If the leftover set contains no letters
            cleanwordlist.add(word)     # Add the word to the list of words matching the criteria

    # Finally return the wordlist of possible words

    return cleanwordlist


def user_input():
    print("--------------")
    print("Running solver.py")
    print("--------------")

    anagram = input("Please enter a valid conundrum? ")     # Takes the user input

    while checkvalidconundrum(anagram) is False:    # Checks whether the user word is valid if not, try again
        print("\nThe conundrum didn't meet the criteria, try again")
        anagram = input("Please enter a valid conundrum? ")

    anagram = anagram.upper()   # Converts the word to uppercase

    print("Valid Anagram: ", anagram)       # Print success message and return the valid, uppercase word

    return anagram


def random_input():
    print("--------------")
    print("Running solver.py")
    print("--------------")

    vowels = list("AEIOU")      # List of vowels
    consonants = list("BCDFGHJKLMNPQRSTVWXYZ")      # List of consonants

    count = 0

    word = ""

    word += (random.choice(consonants))     # Added the first random consonant
    word += (random.choice(consonants))     # Added the second random consonant
    word += (random.choice(consonants))     # Added the third random consonant
    word += (random.choice(consonants))     # Added the fourth random consonant

    while count < word.count('Q'):      # Checks if the word has any Q's if so...
        word += 'U'     # Add a corresponding U
        count += 1      # Mark that we added a vowel (U)

    while count < 3:    # While count of vowels is less then 3 (could have increased because of the Q's and added U's)
        word += (random.choice(vowels))     # Add a random vowel
        count += 1      # Mark that a vowel was added

    # Now 7/9 letters have been added we randomly choose the last 2 letters to add

    secondlast = random.randint(0, 1)       # Random integer between 0 and 1, 0 for a vowel or 1 for a consonant
    last = random.randint(0, 1)     # Random integer between 0 and 1, 0 for a vowel or 1 for a consonant

    if last == 0:
        word += (random.choice(vowels))
    else:
        word += (random.choice(consonants))

    if secondlast == 0:
        word += (random.choice(vowels))
    else:
        word += (random.choice(consonants))

    # Return the finished 9 letter word meeting the criteria

    return word


def reset():
    global anagramFound
    global dictionary
    global anagram
    global iterations

    # Reset the globals for running the program again

    anagramFound = False
    dictionary = set()
    anagram = ""
    iterations = 0


def main_program():

    # Calls the main program method

    return algorithm()


def preprocessing(anagram):
    global dictionary

    print("Random Anagram: ", " ".join(anagram))

    dictionary = preproc.import_dictionaryfile("wordfile.txt")  # Importing the dictionary file
    dictionary = dropwords(anagram, dictionary)     # See the dropwords method

    dictionary = sorted(dictionary, key=len, reverse=True)      # Orders the dictionary by length descending

    return dictionary


def algorithm():
    global dictionary
    global anagram

    tempdictionary = dictionary

    wordfound = "No word found"     # Default word
    count = 0

    t0 = time.time()

    # NB at this point we know the dictionary is in descending order my length

    print()
    print("Size of dictionary with 9 letter words: ", len(tempdictionary))

    for word in tempdictionary:     # For each word in the wordlist dictionary

        count += 1      # Increase the count, just for tracking the amount of iterations

        if collections.Counter(word) == collections.Counter(anagram):

            # If the word contains the same amount of matching letters as the anagram

            print("\nFound a nine letter conundrum!")
            wordfound = word
            break

        if len(word) != 9:  # Once you reach
            break
        else:

            # The word isn't a 9 letter anagram so remove it from the dictionary

            tempdictionary.remove(word)

    print("Size of dictionary with 9 letter words removed: ", len(tempdictionary))

    # Above gets 9 letter conundrum
    # -----------------------------

    for word in tempdictionary:

        count += 1

        if not collections.Counter(word) - collections.Counter(anagram):

            # Removes the anagram letters from the word
            # If any letters are left over in the collections.Counter(word) it's not an anagram and returns true because there is information in the counter
            # Otherwise the counter is empty which means we have a valid anagram

            print()
            print("collections.Counter(word): ", collections.Counter(word))
            print("collections.Counter(anagram): ", collections.Counter(anagram))
            print("collections.Counter(word) - collections.Counter(anagram): ", collections.Counter(word) - collections.Counter(anagram))
            wordfound = word
            break

    print()
    print("First word found: --> {} <-- word size {} ".format(wordfound.title(), len(wordfound)))
    print("\nTime: ", time.time() - t0)
    print("\nCount: ", count)

    return {'Time': time.time() - t0}


# Main Program


def fullprogram():

    global anagramFound
    global dictionary
    global anagram

    anagramFound = False

    dictionary = set()
    anagram = ""

    # Input

    anagram = random_input()
    #anagram = user_input()

    preprocessing(anagram)

    return main_program()


fullprogram()
