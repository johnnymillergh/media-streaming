// noinspection JSUnusedGlobalSymbols
const vm = new Vue({
    el: '#app',
    data: {},
    methods: {
        handleClickGoToHome: function () {
            const currentUrl = window.location.href;
            const split = currentUrl.split('/');
            window.location.href = `${split[0]}//${split[2]}/${split[3]}`;
        }
    }
});
