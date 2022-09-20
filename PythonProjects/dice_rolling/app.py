import random


def roll_dice():

    
    roll = input("Roll the dice (y/n):")

    while roll.lower() == "y".lower():
        dice1 = random.randint(1,6)
        dice2 = random.randint(1,6)

        print(f"dice rolled: {dice1} and {dice2}")

        roll = input("Roll again? (y/n)")

roll_dice()
