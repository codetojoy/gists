'use strict';

const path = require('path');

const DIST_DIR = path.resolve(__dirname + '/dist');
const APP_DIR = path.resolve(__dirname + '/app');

const HtmlPlugin = require('html-webpack-plugin');
const webpack = require('webpack'); // for built-in plugins

module.exports = function(env) {
    return {
        entry: {
            main: APP_DIR + '/index.jsx',
            vendor: ['react', 'react-dom'],
        },
        output: {
            filename: '[chunkhash].[name].js',
            path: DIST_DIR
        },
        devServer: {
            inline: true
          , contentBase: DIST_DIR
          , port: 3333 
        },
        module: {
            rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                exclude: /node_modules/,
                query: {
                    presets: ['react', 'es2015']
            },
            }],

            loaders: [
                {
                    test: /\.jsx?$/,
                    loader: 'babel-loader',
                    exclude: /node_modules/,
                    query: {
                        presets: ['react', 'es2015']
                    }
                }
            ],
        },
        plugins: [
            new HtmlPlugin({ title: 'CustomTitle', 
                                template: APP_DIR + '/index.html', 
                                inject: 'body' }),
            new webpack.optimize.UglifyJsPlugin(),
            new webpack.optimize.CommonsChunkPlugin({
                names: ['vendor', 'manifest'] 
            })
        ]
    }
};
