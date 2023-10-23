var express = require('express');
var app = express();

// This is the definition of the Product class -- DO NOT CHANGE IT!
class Product {
    constructor(id, status) {
		this.id = id;
		this.status = status;
    }
}

// This is the map of IDs to Product objects -- DO NOT CHANGE IT!
var products = new Map();
products.set('1234', new Product('1234', 'available'));
products.set('5678', new Product('5678', 'out of stock'));
products.set('1111', new Product('1111', 'discontinued'));
products.set('4321', new Product('4321', 'available'));
products.set('5555', new Product('5555', 'pre-order'));
products.set('3530', new Product('3530', 'discontinued'));


// This is the '/test' endpoint that you can use to check that this works
// Do not change this, as you will want to use it to check the test code in Part 2
app.use('/test', (req, res) => {
	// create a JSON object
	var data = { 'message' : 'It works!' };
    // send it back
	res.json(data);
    });


// This is the endpoint you need to implement in Part 1 of this assignment
app.use('/status', (req, res) => {
	
	var id = req.query.id;
	var data = [];
	if (id){
		if (Array.isArray(id)){
			id.forEach((v, i) => {
				if (products.has(v)){
					data.push({ "id" : v , "status" : products.get(v).status});
				}
				else{
					data.push({ "id" : v , "status" : "unknown"});
				}
			});
		}
		else{
			data.push({ "id" : id , "status" : products.get(id).status});
		}
	}
    // send it back
	res.json(data);

    });


// -------------------------------------------------------------------------
// DO NOT CHANGE ANYTHING BELOW HERE!



// This just sends back a message for any URL path not covered above
app.use('/', (req, res) => {
	res.send('Default message.');
    });

// This starts the web server on port 3000. 
app.listen(3000, () => {
	console.log('Listening on port 3000');
    });
