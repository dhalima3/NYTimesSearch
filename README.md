# NYTimesSearch
# Project 2 - NYTimesSearch

**NYTimesSearch** is an android app that allows users to search for articles using the New York Times API.

Time spent: __ hours

## User Stories

The following **required** functionality is completed:

* [x] User can **search for news article** by specifying a query and launching a search. Search displays a grid of image results from the New York Times Search API.
  * [x] Used the **ActionBar SearchView** or custom layout as the query box
* [ ] User can click on "settings" which allows selection of **advanced search options** to filter results
* [ ] User can configure advanced search filters such as:
  * [ ] Begin Date (using a date picker)
  * [ ] News desk values (Arts, Fashion & Style, Sports)
  * [ ] Sort order (oldest or newest)
* [ ] Subsequent searches have any filters applied to the search results
* [x] User can tap on any image in results to see the full text of article **full-screen**
* [ ] User can **scroll down to see more articles**. The maximum number of articles is limited by the API search.
* [ ] User can **share an article link** to their friends or email it to themselves

The following **optional** features are implemented:

* [ ] Improved the user interface and experiment with image assets and/or styling and coloring
* [ ] Replaced Filter Settings Activity with a lightweight modal overlay
* [ ] Implements robust error handling, [check if internet is available](http://guides.codepath.com/android/Sending-and-Managing-Network-Requests#checking-for-network-connectivity), handle error cases, network failures
* [ ] Use the [RecyclerView](http://guides.codepath.com/android/Using-the-RecyclerView) with the `StaggeredGridLayoutManager` to display improve the grid of image results
* [ ] For different news articles that only have text or only have images, use [Heterogenous Layouts](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) with RecyclerView
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce view boilerplate.
* [ ] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).
* [ ] Replace Picasso with [Glide](http://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en) for more efficient image rendering.
* [ ] Leverage the popular [GSON library](http://guides.codepath.com/android/Using-Android-Async-Http-Client#decoding-with-gson-library) to streamline the parsing of JSON data.
* [ ] Leverage the popular [Retrofit networking library](http://guides.codepath.com/android/Consuming-APIs-with-Retrofit) to access the New York Times API.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes
