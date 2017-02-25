
'use strict';

function buildConfig(env) {
    let config_file = require('./' + env + '.js');
    return config_file({ env: env });
}

module.exports = buildConfig;
