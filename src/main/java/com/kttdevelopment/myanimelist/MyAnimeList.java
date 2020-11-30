package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.property.AnimeRankingType;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.auth.MyAnimeListAuthenticator;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.manga.Manga;
import com.kttdevelopment.myanimelist.manga.property.MangaRankingType;
import com.kttdevelopment.myanimelist.query.*;
import com.kttdevelopment.myanimelist.user.User;

import java.io.IOException;
import java.util.List;

/**
 * The MyAnimeList API interface, used to execute requests with the API from Java.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MyAnimeList {

    /**
     * Creates an interface with an OAuth token.
     *
     * @param token OAuth token
     *
     * @return MyAnimeList
     *
     * @see #withAuthorization(MyAnimeListAuthenticator)
     * @since 1.0.0
     */
    public static MyAnimeList withOAuthToken(final String token){
        return new MyAnimeListImpl(token);
    }

    /**
     * Creates an interface with an authenticator.
     *
     * @param authenticator authenticator
     * @return MyAnimeList
     *
     * @see #withOAuthToken(String)
     * @see #refreshOAuthToken()
     * @see MyAnimeListAuthenticator
     * @since 1.0.0
     */
    public static MyAnimeList withAuthorization(final MyAnimeListAuthenticator authenticator){
        return new MyAnimeListImpl(authenticator);
    }

    /**
     * Refreshes the OAuth token. Only works with {@link #withAuthorization(MyAnimeListAuthenticator)}.
     *
     * @throws IOException if client could not contact auth server
     *
     * @since 1.0.0
     */
    public abstract void refreshOAuthToken() throws IOException;

    // anime

    /**
     * Returns an Anime search query.
     *
     * @return Anime search
     *
     * @see AnimeSuggestionQuery
     * @see AnimeSuggestionQuery#search()
     * @see com.kttdevelopment.myanimelist.anime.AnimePreview
     * @since 1.0.0
     */
    public abstract AnimeSearchQuery getAnime();

    /**
     * Returns the full Anime details given an ID.
     *
     * @throws InvalidParametersException if parameters are invalid
     * @throws InvalidAuthException if auth token is invalid or expired
     * @throws ConnectionForbiddenException if the server does not allow the request
     * @throws FailedRequestException if the client failed to execute the request
     * @param id Anime id
     * @return Anime
     *
     * @see Anime
     * @see #getAnime(long)
     * @since 1.0.0
     */
    public abstract Anime getAnime(final long id);

    /**
     * Returns Anime details requested in the fields given an ID.
     *
     * @throws InvalidParametersException if parameters are invalid
     * @throws InvalidAuthException if auth token is invalid or expired
     * @throws ConnectionForbiddenException if the server does not allow the request
     * @throws FailedRequestException if the client failed to execute the request
     * @param id Anime id
     * @param fields the fields that should be returned
     * @return Anime
     *
     * @see Anime
     * @see #getAnime()
     * @since 1.0.0
     */
    public abstract Anime getAnime(final long id, final String... fields);

    // anime ranking

    /**
     * Returns an Anime ranking query.
     *
     * @param rankingType ranking type
     * @return ranked Anime
     *
     * @see AnimeRankingQuery
     * @see AnimeRankingQuery#search()
     * @see com.kttdevelopment.myanimelist.anime.AnimeRanking
     * @see AnimeRankingType
     * @since 1.0.0
     */
    public abstract AnimeRankingQuery getAnimeRanking(final AnimeRankingType rankingType);

    // anime season

    /**
     * Returns an Anime season query.
     *
     * @param year year
     * @param season airing season
     * @return seasonal Anime
     *
     * @see AnimeSeasonQuery
     * @see AnimeSeasonQuery#search()
     * @see com.kttdevelopment.myanimelist.anime.AnimePreview
     * @see Season
     * @since 1.0.0
     */
    public abstract AnimeSeasonQuery getAnimeSeason(final int year, final Season season);

    // anime suggestions

    /**
     * Returns an Anime suggestions query.
     *
     * @return suggested Anime
     *
     * @see AnimeSuggestionQuery
     * @see AnimeSuggestionQuery#search()
     * @see com.kttdevelopment.myanimelist.anime.AnimePreview
     * @since 1.0.0
     */
    public abstract AnimeSuggestionQuery getAnimeSuggestions();

    // anime list

    /**
     * Returns an Anime listing updater.
     *
     * @param id Anime id
     * @return Anime list updater
     *
     * @see AnimeListUpdate
     * @see AnimeListUpdate#update()
     * @see #deleteAnimeListing(long)
     * @see #getUserAnimeListing()
     * @see #getUserAnimeListing(String)
     * @since 1.0.0
     */
    public abstract AnimeListUpdate updateAnimeListing(final long id);

    /**
     * Removes an Anime listing.
     *
     * @throws InvalidAuthException if auth token is invalid or expired
     * @throws ConnectionForbiddenException if the server does not allow the request
     * @throws FailedRequestException if the client failed to execute the request
     * @param id Anime id
     *
     * @see #updateAnimeListing(long)
     * @see #getUserAnimeListing()
     * @see #getUserAnimeListing(String)
     * @since 1.0.0
     */
    public abstract void deleteAnimeListing(final long id);

    /**
     * Returns a list query for your Anime listings.
     *
     * @return Anime listing
     *
     * @see UserAnimeListQuery
     * @see UserAnimeListQuery#search()
     * @see #updateAnimeListing(long)
     * @see #deleteAnimeListing(long)
     * @see #getUserAnimeListing(String)
     * @since 1.0.0
     */
    public abstract UserAnimeListQuery getUserAnimeListing();

    /**
     * Returns a list query for a user's Anime listings.
     *
     * @param username username
     * @return Anime listing
     *
     * @see UserAnimeListQuery
     * @see UserAnimeListQuery#search()
     * @see #updateAnimeListing(long)
     * @see #deleteAnimeListing(long)
     * @see #getUserAnimeListing()
     * @since 1.0.0
     */
    public abstract UserAnimeListQuery getUserAnimeListing(final String username);

    // forum

    /**
     * Returns the top level forum boards.
     *
     * @return forum boards
     *
     * @see ForumCategory
     * @since 1.0.0
     */
    public abstract List<ForumCategory> getForumBoards();

    //

    /**
     * Returns a forum topic.
     *
     * @param id forum topic id
     * @return forum topic
     *
     * @see #getForumTopicDetail(long, int)
     * @see #getForumTopicDetail(long, int, int)
     * @since 1.0.0
     */
    public abstract ForumTopic getForumTopicDetail(final long id);


    /**
     * Returns a forum topic.
     *
     * @deprecated The limit parameter is listed on the API but doesn't actually do anything
     * @param id forum topic id
     * @param limit limit
     * @return forum topic
     *
     * @see #getForumTopicDetail(long)
     * @see #getForumTopicDetail(long, int, int)
     * @since 1.0.0
     */
    @Deprecated
    public abstract ForumTopic getForumTopicDetail(final long id, final int limit);

    /**
     * Returns a forum topic.
     *
     * @deprecated The limit and offset parameters are listed on the API but it doesn't actually do anything
     * @param id forum topic id
     * @param limit limit
     * @param offset offset
     * @return forum topic
     *
     * @see #getForumTopicDetail(long)
     * @see #getForumTopicDetail(long, int)
     * @since 1.0.0
     */
    @Deprecated
    public abstract ForumTopic getForumTopicDetail(final long id, final int limit, final int offset);

    //

    /**
     * Returns a forum topic search query.
     *
     * @return forums
     *
     * @see ForumSearchQuery
     * @see ForumSearchQuery#search()
     * @since 1.0.0
     */
    public abstract ForumSearchQuery getForumTopics();

    // manga

    /**
     * Returns a Manga search query.
     *
     * @return Manga search
     *
     * @see MangaSearchQuery
     * @see MangaSearchQuery#search()
     * @see com.kttdevelopment.myanimelist.manga.MangaPreview
     * @since 1.0.0
     */
    public abstract MangaSearchQuery getManga();

    /**
     * Returns the full Manga details given an ID.
     *
     * @throws InvalidParametersException if parameters are invalid
     * @throws InvalidAuthException if auth token is invalid or expired
     * @throws ConnectionForbiddenException if the server does not allow the request
     * @throws FailedRequestException if the client failed to execute the request
     * @param id Manga id
     * @return Manga
     *
     * @see Manga
     * @see #getManga(long)
     * @since 1.0.0
     */
    public abstract Manga getManga(final long id);

    /**
     * Returns Manga details requested in the fields given an ID.
     *
     * @throws InvalidParametersException if parameters are invalid
     * @throws InvalidAuthException if auth token is invalid or expired
     * @throws ConnectionForbiddenException if the server does not allow the request
     * @throws FailedRequestException if the client failed to execute the request
     * @param id Manga id
     * @param fields the fields that should be returned
     * @return Manga
     *
     * @see Manga
     * @see #getManga()
     * @since 1.0.0
     */
    public abstract Manga getManga(final long id, final String... fields);

    // manga ranking

    /**
     * Returns a Manga ranking query.
     *
     * @param rankingType ranking type
     * @return ranked Manga
     *
     * @see MangaRankingQuery
     * @see MangaRankingQuery#search()
     * @see com.kttdevelopment.myanimelist.manga.MangaRanking
     * @see MangaRankingType
     * @since 1.0.0
     */
    public abstract MangaRankingQuery getMangaRanking(final MangaRankingType rankingType);

    // manga list

    /**
     * Returns a Manga listing updater.
     *
     * @param id Manga id
     * @return Manga list updater
     *
     * @see MangaListUpdate
     * @see MangaListUpdate#update()
     * @see #deleteMangaListing(long)
     * @see #getUserMangaListing()
     * @see #getUserMangaListing(String)
     * @since 1.0.0
     */
    public abstract MangaListUpdate updateMangaListing(final long id);

    /**
     * Removes a Manga listing.
     *
     * @throws InvalidAuthException if auth token is invalid or expired
     * @throws ConnectionForbiddenException if the server does not allow the request
     * @throws FailedRequestException if the client failed to execute the request
     * @param id Manga id
     *
     * @see #updateMangaListing(long)
     * @see #getUserMangaListing()
     * @see #getUserMangaListing(String)
     * @since 1.0.0
     */
    public abstract void deleteMangaListing(final long id);

    /**
     * Returns a list query for your Manga listings.
     *
     * @return Manga listing
     *
     * @see UserMangaListQuery
     * @see UserMangaListQuery#search()
     * @see #updateMangaListing(long)
     * @see #deleteMangaListing(long)
     * @see #getUserMangaListing(String)
     * @since 1.0.0
     */
    public abstract UserMangaListQuery getUserMangaListing();

    /**
     * Returns a list query for a user's Manga listings.
     *
     * @param username username
     * @return Manga listing
     *
     * @see UserMangaListQuery
     * @see UserMangaListQuery#search()
     * @see #updateMangaListing(long)
     * @see #deleteMangaListing(long)
     * @see #getUserMangaListing()
     * @since 1.0.0
     */
    public abstract UserMangaListQuery getUserMangaListing(final String username);

    // user

    /**
     * Returns the authenticated user.
     *
     * @return user
     *
     * @see User
     * @see #getMyself(String[])
     * @since 1.0.0
     */
    public abstract User getMyself();

    /**
     * Returns the authenticated user.
     *
     * @param fields the fields to return
     * @return user
     *
     * @see User
     * @see #getMyself()
     * @since 1.0.0
     */
    public abstract User getMyself(final String[] fields);

    /**
     * Returns a user given their username.
     *
     * @throws InvalidParametersException if parameters are invalid
     * @throws InvalidAuthException if auth token is invalid or expired
     * @throws ConnectionForbiddenException if the server does not allow the request
     * @throws FailedRequestException if the client failed to execute the request
     * @param username username
     * @return user
     *
     * @see User
     * @see #getUser(String, String...)
     * @since 1.0.0
     */
    public abstract User getUser(final String username);

    /**
     * Returns a user given their username.
     *
     * @throws InvalidParametersException if parameters are invalid
     * @throws InvalidAuthException if auth token is invalid or expired
     * @throws ConnectionForbiddenException if the server does not allow the request
     * @throws FailedRequestException if the client failed to execute the request
     * @param username username
     * @param fields the fields to return
     * @return user
     *
     * @see User
     * @see #getUser(String)
     * @since 1.0.0
     */
    public abstract User getUser(final String username, final String... fields);

}
