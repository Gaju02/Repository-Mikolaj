
def main():
    print("This program convert US dollars to Pounds Sterling")
    print()
    dollars = eval(input("Enter amount in dollars: "))

    pounds = convert_to_pounds(dollars)

    print(f"That is {pounds} pounds")
convert_to_pounds = lambda dollars: dollars * 0,86 

main()