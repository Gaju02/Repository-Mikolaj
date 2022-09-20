import random

def play():
    user = input(" Type your choice 'r' for rock, 'p' for papers, 's' for scissors  :  ")
    computer = random.choice(['r','p','s'])

    if user == computer:
        return 'tie'
    
    #r>s, s>p , p>r
    if is_win(user,computer):
        return 'You won!'
    
    return 'You lost! Computer win'

def is_win(player, opponent):
    if (player == 'r' and opponent == 's') \
        or (player == 's' and opponent=='p') \
            or (player=='p' and opponent=='r'):
                return True

print(play())
        