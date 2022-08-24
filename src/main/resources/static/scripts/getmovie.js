function getMovie() {
    const {data} = axios({
        method: 'get',
        url: 'http://localhost:8080/api/v1/movies/',
        params: {
            movieTitle: document.getElementById('movieTitle').value,
            released: document.getElementById('released').value
        },
        responseType: 'application/json'
    }).then(function (response) {
        console.log(response)
        document.getElementById('title').textContent = 'Title: ' + response.data.title
        document.getElementById('releasedate').textContent = 'Released: ' + response.data.released
        document.getElementById('plot').textContent = 'Plot: ' + response.data.plot
        document.getElementById('genre').textContent = 'Genre: ' + response.data.genre
        document.getElementById('director').textContent = 'Director: ' + response.data.director
        document.getElementById('like-btn').style.visibility = "visible"
        document.getElementById('poster').src = response.data.poster
        document.getElementById('poster').style.visibility = "visible"
    }).catch(function (error) {
        console.log(error);
    });
}