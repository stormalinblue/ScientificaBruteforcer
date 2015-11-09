import itertools
import time
from flask import Flask,render_template, jsonify, request

app = Flask(__name__)
password = ""
donepassword = ""

@app.route("/")
def home():
	return render_template("bruteforce.html")

@app.route("/hack")
def hack():
    return render_template("crackpassword.html")

@app.route('/bruteforce')
def realhack():
	password = request.args.get('pwd')
	#donepassword = mainrun()
	outputfile = open("output.txt", "r+")
	outputfile.write("{}".format(password))
	outputfile.close()
	return jsonify(result=donepassword)

@app.route('/realhackingrequest')
def realhackingrequest():
	inputfile = open("output.txt", "r+")
	boogie = inputfile.read(4)
	return jsonify(p=boogie)
	
def bruteforce(charset, maxlength):
    return (''.join(candidate)
        for candidate in itertools.chain.from_iterable(itertools.product(charset, repeat=i)
        for i in range(1, maxlength + 1)))

def mainrun():
	L = list(bruteforce('abcdefghijklmnopqrstuvwxyz0123456789', 4))
	for i in range (len(L)):
		if L[i] == password:
			return L[i]

if __name__ == "__main__":
	app.run(debug=True)