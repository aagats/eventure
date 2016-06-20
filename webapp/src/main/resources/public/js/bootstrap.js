require.config({
    paths: {
        jquery: 'vendor/jquery-1.10.2.min',
        underscore: 'vendor/lodash',
        backbone: 'vendor/backbone.min',
        text: 'vendor/plugins/text',
        bootstrap: 'vendor/bootstrap.min'
    },
    shim: {
        bootstrap: {deps : ['jquery'], exports: '$'}
    }

});

require([
    'app'
], function(App){
    App.initialize();
});