'use strict';

var path = require('path')

var DIST_DIR = path.resolve(__dirname + '/dist') 
var APP_DIR = path.resolve(__dirname + '/app')

const HtmlPlugin = require('html-webpack-plugin');

module.exports = {
    devServer: {
        inline: true
      , contentBase: DIST_DIR
      , port: 3333 
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                loader: 'babel',
                exclude: /node_modules/,
                query: {
                    presets: ['react', 'es2015']
                }
            }
        ],
    },
    plugins: [new HtmlPlugin({
        title: 'CustomTitle',
        template: APP_DIR + '/index.html', // Load a custom template
        inject: 'body' // Inject all scripts into the body
    })],
    entry: APP_DIR + '/index.jsx',
    output: {
        path: `${__dirname}/dist`,
        filename: 'bundle.js'
    }
};
