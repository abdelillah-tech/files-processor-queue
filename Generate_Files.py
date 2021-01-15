import time
import string
import random


def generate():
    execute = True
    i = 0
    N = 50
    try:
        while True:
            text_random = ''.join(random.choices(string.ascii_uppercase + string.digits, k=N))
            title = "./generatedFiles/file{}.txt".format(i)
            f = open(title, "w+")
            f.write("text aleatoire numero {}  :  ".format(i) + str(text_random))
            i = i + 1
            time.sleep(5)
    except KeyboardInterrupt:
        print('interrupted!')


if __name__ == '__main__':
    generate()
