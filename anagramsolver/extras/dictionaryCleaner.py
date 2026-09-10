# Created for 4th year Theory of Algorithms project
# by SoftDevAndy Feburary 2016

# Parses a file of words and keeps only the words
# that are between 2 and 10 characters. It only adds
# words to the dictionary that contain no whitespaces,
# no numbers and no special characters.

# Only used for the creation of clean wordfiles for importing into the project
# Not meant to be efficient just a rough way to create the files the way I need them

# Function Definitions


def print_dictionary(word_list):
    print()
    for wrd in word_list:
        print(wrd)


def export_list(word_list):
    word_list.sort()
    file_name = 'cleanwords.txt'
    w = open(file_name, 'w')

    for line in word_list:
        w.write(line + "\n")

    w.close


def remove_duplicates(word_list):

    # Found this for finding duplicates easily
    # http://stackoverflow.com/questions/7961363/python-removing-duplicates-in-lists

    word_list = list(set(word_list))

    word_list.sort()

    return word_list


def import_file(file_name):

    with open(file_name, 'r') as filein:
        wordlist = filein.readlines()
    filein.close()

    return wordlist


def cleanse(cleanme):

    cleanme = ''.join(e for e in cleanme if e.isalnum())

    # Above line comes from the article below
    # http://stackoverflow.com/questions/5843518/remove-all-special-characters-punctuation-and-spaces-from-string

    cleanme = cleanme.strip()
    cleanme = cleanme.upper()
    return cleanme


def allowed(checkme):

    word_size = len(checkme)

    if checkme.isalpha() is False:
        return False

    if 3 <= word_size <= 9:
        return True
    else:
        return False


# Program

dirty_dictionary = []
clean_dictionary = []

file_to_import = 'wordfile.txt'  # Change this to the text file you wish to import

dirty_dictionary = import_file(file_to_import)

for word in dirty_dictionary:

    cleanedword = cleanse(word)

    if allowed(cleanedword):
        clean_dictionary.append(cleanedword)

clean_dictionary = remove_duplicates(clean_dictionary)

export_list(clean_dictionary)

print('Done')
#print("Dirty dictionary count",len(dirty_dictionary))
#print("Clean dictionary count",len(clean_dictionary))