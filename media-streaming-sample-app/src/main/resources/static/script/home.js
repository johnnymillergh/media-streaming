// noinspection JSUnusedGlobalSymbols
const vm = new Vue({
    el: '#app',
    data: {
        projectArtifactId: 'Media Streaming :: Sample App',
        newPerson: {
            name: '',
            age: 0,
            sex: 'Male'
        },
        people: [{
            name: 'Jack',
            age: 30,
            sex: 'Male'
        }, {
            name: 'Bill',
            age: 26,
            sex: 'Male'
        }, {
            name: 'Tracy',
            age: 22,
            sex: 'Female'
        }, {
            name: 'Chris',
            age: 36,
            sex: 'Male'
        }]
    },
    methods: {
        /**
         * Create a person
         */
        createPerson: function () {
            this.people.push(this.newPerson);
            this.newPerson = {name: '', age: 0, sex: 'Male'}

        },
        /**
         * delete a person
         * @param index
         */
        deletePerson: function (index) {
            this.people.splice(index, 1);
        },
        handleClickSwaggerApiDocumentation: function () {
            const currentUrl = window.location.href;
            const split = currentUrl.split('/');
            window.location.href = `${split[0]}//${split[2]}/doc`;
        },
        handleClickVideoDemo: function () {
            const currentUrl = window.location.href;
            const split = currentUrl.split('/');
            window.location.href = `${split[0]}//${split[2]}/${split[3]}/video.html`;
        }
    }
});
