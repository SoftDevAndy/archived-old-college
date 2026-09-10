def export_list(word_list,file_name):
    word_list.sort()
    w = open(file_name, 'w')

    for line in word_list:
        w.write(line + "\n")

    w.close


def import_file(file_name):

    wordlist = []

    with open(file_name,'r') as f:
        for line in f:
            for word in line.split():
               wordlist.append(word.upper())

    return wordlist

# Program

clean_dictionary = []
dictionary = import_file("removenouns_wordfile.txt")
nouns = import_file("removenouns_propernouns.txt")

nouns = set(nouns)

print(len(dictionary))
print(len(nouns))

for word in dictionary:
    if word not in nouns:
        clean_dictionary.append(word)

export_list(clean_dictionary, "wordfileclean.txt")

print(len(clean_dictionary))