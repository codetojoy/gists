
'use strict';

const webpack = require('webpack');
const path = require('path');
const HtmlPlugin = require('html-webpack-plugin');

module.exports = function(env) {
    return {
        entry: {
            main: './index.js',
            vendor: 'moment'
        },
        output: {
            filename: '[chunkhash].[name].js',
            path: path.resolve(__dirname, 'dist')
        },
        plugins: [
            new HtmlPlugin({ title: 'CustomTitle', 
                             template: './index.html', 
                             inject: 'body' }),
            new webpack.optimize.CommonsChunkPlugin({
                names: ['vendor', 'manifest'] 
            })
        ]
    }
};
