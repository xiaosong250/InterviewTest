/***
 * Part I. Flatten a nested JS Object.
 */

var jsonParser = function (jsonData) {
    var pKey = '';
    var outPut = {};

    var jsonFlatter = function (data, key, output) {
        for (var i in data) {
            if (data.hasOwnProperty(i) && data[i].constructor.name === 'Object') {
                jsonFlatter(data[i], i, output);
            } else {
                var j = key === '' ? i : key + '.' + i;
                output[j] = data[i];
            }
        }
    };

    jsonFlatter(jsonData, pKey, outPut);
    return outPut;
};

var m = {"a": 1, "b": {"c": {"c1": 2, "c2": 2.5}, "d": [3, 4]}, 'e': 5};

/**********************************************/

/***
 * Part II. Switch data format between String and list of Object.
 */

var store = function (a) {
    var output = "";
    for (var i = 0; i < a.length; i++) {
        for (var key in a[i]) {
            if (a[i].hasOwnProperty(key)) {
                output += key;
                output += "=";
                output += a[i][key];
                output += ";";
            }
        }
        output = output.slice(0, -1);
        output += "\n";
    }
    return output;
};

var load = function (text) {
    var arr = text.split("\n");
    var output = [];
    for (var i = 0; i < arr.length; i++) {
        var items = arr[i].split(";");
        var obj = {};
        for (var j = 0; j < items.length; j++) {
            var item = items[j].split("=");
            obj[item[0]] = item[1];
        }
        output.push(obj);
    }
    return output;
};

var text = "key1=value1;key2=value2\nkeyA=valueA\nkeyB=valueB";
var a = [{"key1": "value1", "key2": "value2"}, {"keyA": "valueA"}, {"keyB": "valueB"}];
