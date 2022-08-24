function getTopLikedMovies() {
    const {data} = axios({
        method: 'get',
        url: 'http://localhost:8080/api/v1/movies/top-liked-movies',
        responseType: 'application/json'
    }).then(function (response) {
        console.log(response)
    }).catch(function (error) {
        console.log(error);
    });
}