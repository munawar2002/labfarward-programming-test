function config() {
    var env = karate.env; // get system property 'karate.env'
    karate.log('Local api tests are running:', env);

    karate.configure('logPrettyResponse', true);
    karate.configure('logPrettyRequest', true);

    return {
        env: env,
        HOST: 'http://localhost:8080/',
    };
}
