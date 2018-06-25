import {NativeModules} from 'react-native';

var RETRY_DELAY = 2000;
var NULL_ACTIVITY_MESSAGE = "Activity is null";

var methods = [
	"openSettings",
	"bringApptoBackground",
	"bringApptoForeground",
	"lightScreen",
	"clearWindow",
	"isLocked"
];

module.exports = methods.reduce(function(exports, method) {
	return NativeModules.BackgroundCheckModule[method]().catch(function(message) {
		if(message === NULL_ACTIVITY_MESSAGE) {
			return wait(RETRY_DELAY).then(function(){
				return NativeModules.BackgroundCheckModule[method]();
			})
		} else {
			throw message;
		}
	});
},{})

function wait(n) {
	return new Promise(function(resolve){
		setTimeout(resolve, n);
	});
}
