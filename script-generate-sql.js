const axios = require('axios');

for (i=1; i < 6; i++) { 
    axios.get(`https://www.omdbapi.com/?s="and"&page=${i}&type=movie&apikey=1372ca2`).then(function (response) { 
        // console.log(response.data.Search.length)
        for (j=0; j < response.data.Search.length ;j++) { 
            var id = response.data.Search[j].imdbID
            axios.get(`https://www.omdbapi.com/?i=${id}&apikey=1372ca2`).then(function (omdbapiResponse) { 
                var omdbapi = omdbapiResponse.data
                console.log(`insert into movie(title,external_id,rating, votes) values ('${omdbapi.Title}', '${omdbapi.imdbID}', ${omdbapi.imdbRating.replace(",", ".")}, ${omdbapi.imdbVotes.replace(",", ".")});`)
            });
        }
    });
}


