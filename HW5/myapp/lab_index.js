var express = require('express');
var app = express();
var map = new Map();


// This is the '/test' endpoint that you can use to check that this works
app.use('/test', (req, res) => {
	// create a JSON object
	var data = { 'message' : 'It works!' };
      	// send it back
	res.json(data);
    });

// Square
app.use('/square', (req, res) => {
	// create a JSON object
	var n = req.query.input;
	if (n){
		if (!isNaN(n)){
			n = Number(n);
			var result = n ** 2;
			var data = { "status" : "valid" , "result" : result};
		}
		else{
			var data = { "status" : "not a number"};
		}
	}
	else{
		var data = { "status" : "no number" };
	}
      	// send it back
	res.json(data);
    });

// put
app.use('/put', (req, res) => {
	// create a JSON object
	var key = req.query.key;
	var value = req.query.value;
	if (key && value){
		map.set(key, value)
		var data = { "status" : "mapped " + key + " to " + value };
	}
	else{
		var data = { "status" : "missing data" };
	}
      	// send it back
	res.json(data);
    });

// get
app.use('/get', (req, res) => {
	// create a JSON object
	var key = req.query.key;
	if (key){
		if (map.has(key)){
			var data = { "status" : "found" , "value" : map.get(key)};
		}
		else{
			var data = { "status" : "not found" };
		}		
	}
	else{
		var data = { "status" : "missing data" };
	}
      	// send it back
	res.json(data);
    });

// isPrime
function isPrime(num){
	for (let i = 2; i < num; i++){
		console.log(num);
		console.log(num % i);
		if (num % i == 0){
			return false;
		}
	}
	return true;
}

// primesLessThan
app.use('/primesLessThan', (req, res) => {
	// create a JSON object
	var value = req.query.value;
	var data = [];
	if (value && !isNaN(value)){
		value = Number(value);
		if (value > 2){
			for (let i = 2; i <= value; i++){
				if (isPrime(i)){
					data.push({"number" : i , "prime" : true});
				}
				else{
					data.push({"number" : i , "prime" : false});
				}
			}
		}
	}
    // send it back
	res.json(data);
    });


// This just sends back a message for any URL path not covered above
app.use('/', (req, res) => {
	res.send('Default message.');
    });

// This starts the web server on port 3000. 
app.listen(3000, () => {
	console.log('Listening on port 3000');
    });
