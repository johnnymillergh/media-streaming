// noinspection JSUnusedGlobalSymbols
const vm = new Vue({
    el: '#app',
    data: {
        projectArtifactId: 'Media Streaming :: Sample App',
        videoSource: ''
    },
    async mounted() {
        const currentUrl = window.location.href;
        const split = currentUrl.split('/');
        this.videoSource = `${split[0]}//${split[2]}/${split[3]}/test-table/demo-video`;
        const baseUrl = `${split[0]}//${split[2]}`;
        const response = await fetch(`${baseUrl}/common/app-info`);
        const responseJson = await response.json();
        this.appInfo = responseJson.data;
    }
});
