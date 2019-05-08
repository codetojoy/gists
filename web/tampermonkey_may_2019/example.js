// ==UserScript==
// @name         My_Sandbox
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @match        http://localhost:5151/egg3_sandbox_no_spring/editScenario.action
// @run-at       document-end
// @require      https://code.jquery.com/jquery-3.4.1.min.js
// @grant        none
// ==/UserScript==

console.log('TRACER value: ' + jQuery('#saveScenario_scenarioBean_fieldP').prop('checked'));
jQuery('#saveScenario_scenarioBean_age').val('5150');
jQuery('#saveScenario_scenarioBean_fieldP').prop('checked', true);
console.log('TRACER value: ' + jQuery('#saveScenario_scenarioBean_fieldP').prop('checked'));
