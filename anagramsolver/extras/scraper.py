# SoftDevAndy 2016 February
# http://www.oxfordlearnersdictionaries.com/wordlist/english/oxford3000/ Pulls dictionary words from this source
# Created as part of a fourth year anagram project

from bs4 import BeautifulSoup
import requests


def export_dictionary(word_list, file_name):
    w = open(file_name, 'w')

    for line in word_list:
        w.write(line + "\n")

    print("File exported")


def scrape_and_build():

    allwords = []

    baseurl = 'http://www.oxfordlearnersdictionaries.com/wordlist/english/oxford3000/Oxford3000_'
    ranges = ['A-B', 'C-D', 'E-G', 'H-K', 'L-N', 'O-P', 'Q-R', 'S', 'T', 'U-Z']
    addendum = '/?page='

    count = 0
    maxpagecount = 5 # The max number of pages each of the letters have is 5

    while count < len(ranges):

        url = baseurl + ranges[count] + addendum
        pagecount = 0

        while pagecount < maxpagecount:
            tempurl = url + str(pagecount)

            response = requests.get(tempurl)
            soup = BeautifulSoup(response.text, "html.parser")

            wordlist = soup.find('ul', {'class': 'result-list1 wordlist-oxford3000 list-plain'})

            if wordlist is not None:

                #http://stackoverflow.com/questions/4362981/beautifulsoup-how-do-i-extract-all-the-lis-from-a-list-of-uls-that-contains

                for li in wordlist.findAll('li'):
                    if li.find('ul'):
                        break
                    allwords.append(li.text.strip('\n'))

            pagecount += 1

        print(ranges[count] + " Done")

        count += 1

    print()
    print(allwords)
    print(len(allwords))

    return allwords

# Program

print("Running")

dictionary = scrape_and_build()

print("Finished Scrape")

export_dictionary(dictionary, "scraper_oxford3000.txt")
