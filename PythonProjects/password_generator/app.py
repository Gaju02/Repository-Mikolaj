import string
import random

characters = list(string.ascii_letters + string.digits + "!@#$%^&*()")

def generate_password(lenght):
    random.shuffle(characters)
    password =[]
    for x in range(lenght):
        password.append(random.choice(characters))
    random.shuffle(password)

    password = "".join(password)

    print(password)


option = input("Do you want to generate password? (y/n)")

if option == 'y':
    lenght = int(input("How long would you like your password to be  : "))
    generate_password(lenght)
elif option == 'n':
    print("Program ended")
    quit()
else:
    print("Invalid input")
    quit()

