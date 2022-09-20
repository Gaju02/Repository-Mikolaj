import random
import string

words = ["aback","abaft","abandoned","abashed","aberrant","abhorrent","abiding","abject","ablaze","able","abnormal","aboard","aboriginal","abortive","abounding","abrasive","abrupt","absent","absorbed","absorbing","abstracted","absurd","abundant","abusive","accept","acceptable","accessible","accidental","account","accurate","achiever","acid","acidic","acoustic","acoustics","acrid","act","action","activity","actor","actually","ad hoc","adamant","adaptable","add","addicted","addition","adhesive","adjoining","adjustment","admire","admit","adorable","adventurous","advertisement","advice","advise","afford","afraid","aftermath","afternoon","afterthought","aggressive","agonizing","agree","agreeable","agreement","ahead","air","airplane","airport","ajar","alarm","alcoholic","alert","alike","alive","alleged","allow","alluring","aloof","amazing","ambiguous","ambitious","amount","amuck","amuse","amused","amusement","amusing","analyze","ancient","anger","angle","angry","animal","animated","announce","annoy","annoyed","annoying","answer","ants","anxious","apathetic","apologise","apparatus","apparel","appear","applaud","appliance","appreciate","approval","approve","aquatic","arch","argue","argument","arithmetic","arm","army","aromatic","arrange","arrest","arrive","arrogant","art","ashamed","ask","aspiring","assorted","astonishing","attach","attack","attempt","attend","attract","attraction","attractive","aunt","auspicious","authority","automatic","available","average","avoid","awake","aware","awesome","awful","axiomatic","babies","baby","back","bad","badge","bag","bait","bake","balance","ball","ban","bang","barbarous","bare","base","baseball","bashful","basin","basket","basketball","bat","bath","bathe","battle","bawdy","bead","beam","bear","beautiful","bed","bedroom","beds","bee","beef","befitting","beg","beginner","behave","behavior","belief","believe","bell","belligerent","bells","belong","beneficial","bent","berry","berserk","best","better","bewildered","big","bike","bikes","billowy","bird","birds","birth","birthday","bit","bite","bite-sized","bitter","bizarre","black","black-and-white","blade","bleach","bless","blind","blink","blood","bloody","blot","blow","blue","blue-eyed","blush","blushing","board","boast","boat","boil","boiling","bolt","bomb","bone","book","books","boorish","boot","border","bore","bored","boring","borrow","bottle","bounce","bouncy","boundary","boundless","bow","box","boy","brainy","brake","branch","brash","brass","brave","brawny","breakable","breath","breathe","breezy","brick","bridge","brief","bright","broad","broken","brother","brown","bruise","brush","bubble","bucket","building","bulb","bump","bumpy","burly","burn","burst","bury","bushes","business","bustling","busy","butter","button","buzz","cabbage","cable","cactus","cagey","cake","cakes","calculate","calculating","calculator","calendar","call","callous","calm","camera","camp","can","cannon","canvas","cap","capable","capricious","caption","car","card","care","careful","careless","caring","carpenter","carriage","carry","cars","cart","carve","cast","cat","cats","cattle","cause","cautious","cave","ceaseless","celery","cellar","cemetery","cent","certain","chalk","challenge","chance","change","changeable","channel","charge","charming","chase","cheap","cheat","check","cheer","cheerful","cheese","chemical","cherries","cherry","chess","chew","chicken","chickens","chief","childlike","children","chilly","chin","chivalrous","choke","chop","chubby","chunky","church","circle","claim","clam","clammy","clap","class","classy","clean","clear","clever","clip","cloistered","close","closed","cloth","cloudy","clover","club","clumsy","cluttered","coach","coal","coast","coat","cobweb","coherent","coil","cold","collar","collect","color","colorful","colossal","colour","comb","combative","comfortable","command","committee","common","communicate","company","compare","comparison","compete","competition","complain","complete","complex","concentrate","concern","concerned","condemned","condition","confess","confuse","confused","connect","connection","conscious","consider","consist","contain","continue"]

def get_valid_word(words):
    word = random.choice(words)
    while '-' in word or ' ' in word:
        word = random.choice(words)
    
    return word.upper()

def hangman():
    word = get_valid_word(words)
    word_letters =set(word)
    alphabet = set(string.ascii_uppercase)
    used_letters = set()

    lives = 7

    while len(word_letters) > 0 and lives > 0:
        print('You have', lives, '.  You have used these letters:', ' '.join(used_letters))

        word_list = [letter if letter in used_letters else '-' for letter in word]
        print('Current word: ',''.join(word_list))

        user_letter = input('Guess a letter: ').upper()
        if user_letter in alphabet - used_letters:
            used_letters.add(user_letter)
            if user_letter in word_letters:
                word_letters.remove(user_letter)
            else:
                lives = lives-1
                print('Letter is not valid.')

        elif user_letter in used_letters:
            print('You have already used that character. Please try again')

        else:
            print('Invalid character. Please try again')
    
    if lives == 0:
        print('Game over. The word was: ',word)
    else:
        print('You guessed the word', word, '!!')

if __name__ == '__main__':
    hangman()


