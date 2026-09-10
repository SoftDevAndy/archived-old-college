# http://deron.meranda.us/data/census-dist-female-first.txt
# http://deron.meranda.us/data/census-dist-male-first.txt

# I combined the above files manually to give 1 large file of both sets of names
# Because of the format of the above text files I created the following short script
# to remove the names and print them out to a new file of just the names.


def print_dictionary(word_list):
    print()
    for wrd in word_list:
        print(wrd)


def export_list(word_list,file_name):
    word_list.sort()
    w = open(file_name, 'w')

    for line in word_list:
        w.write(line + "\n")

    w.close


def import_file(file_name):

    with open(file_name, 'r') as filein:
        wordlist = filein.readlines()
    filein.close()

    return wordlist

# Program

dictionary = []

wordlist = import_file("names.txt")

for line in wordlist:
    dictionary.append(line.split()[0])

export_list(dictionary, "namesclean.txt")

print(len(dictionary))
