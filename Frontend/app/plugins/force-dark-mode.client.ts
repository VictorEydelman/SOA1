export default defineNuxtPlugin((nuxtApp) => {
    nuxtApp.hook('app:mounted', () => {
        const colorMode = useColorMode();
        colorMode.preference = 'dark';
    });
});