require.config({
    paths: {
        jquery: 'vendor/jquery-1.10.2.min.js',
        underscore: 'vendor/lodash',
        backbone: 'vendor/backbone.min.js',
        text: 'vendor/plugins/text',
        bootstrap: '//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'
    }

});

require([
    'app'
], function(App){
    App.initialize();
});