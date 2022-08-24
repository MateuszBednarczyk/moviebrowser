function addAsFavourite() {
    axios({
        method: 'post',
        url: 'http://localhost:8080/api/v1/movies/add-as-favourite',
        data: {
            movieTitle: document.getElementById('movieTitle').value,
            released: document.getElementById('released').value
        }
    }).then(function (response) {
        console.log(response)
        alert('Added as favourite')
    }).catch(function (error) {
        alert(error.message)
        console.log(error);
    });
}
