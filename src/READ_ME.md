## Use case
This tool allows you to use RSS feeding service.
This is also an alternative way to avoid CORS issue if this runs in the same server.

### Endpoint features
- http(s)://{ip/dns}/file/rss: 
  <br>Accepts a name of a file where the path is only known in locally
  <br>User will have to use one of the names from `List of registered names`

  - **Normal Mode**: Returns rss contents in response body with string type.
  - **Large File Mode**: Streams string rss content or only accessible via file download.(Not supported yet)

- web/rss
  <br>Accepts a url path for `https://rss.com/podcasts` or accpets full URL link to fetch external RSS file.


### List of registered names

events.rss

### Todo
- Support large file mode
- Support asynchronous processing on each request