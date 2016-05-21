define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/login-page.html'
], function($, _, Backbone, loginPageTemplate){
    var LoginPageView = Backbone.View.extend({

        render: function(){
            this.$el.html(_.template(loginPageTemplate));
        }
    });
    return LoginPageView;
});