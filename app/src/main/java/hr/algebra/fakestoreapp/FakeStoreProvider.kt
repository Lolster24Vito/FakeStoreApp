package hr.algebra.fakestoreapp

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import hr.algebra.fakestoreapp.dao.FakeStoreRepository
import hr.algebra.fakestoreapp.factory.getFakeStoreRepository
import hr.algebra.fakestoreapp.model.Item

private const val AUTHORITY = "hr.algebra.fakestoreapp.api.provider"
private const val PATH = "items"
val FAKESTORE_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")

private const val ITEMS = 10
private const val ITEM_ID = 20
private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
    addURI(AUTHORITY, PATH, ITEMS)
    addURI(AUTHORITY, "$PATH/#", ITEM_ID)
    this
}
    class FakeStoreProvider : ContentProvider() {

        private lateinit var fakeStoreRepository:FakeStoreRepository
    override fun onCreate(): Boolean {
        fakeStoreRepository = getFakeStoreRepository(context)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? = fakeStoreRepository.query(projection, selection, selectionArgs, sortOrder)

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = fakeStoreRepository.insert(values)
        return ContentUris.withAppendedId(FAKESTORE_PROVIDER_CONTENT_URI, id)
    }

        override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
            when(URI_MATCHER.match(uri)) {
                ITEMS -> return fakeStoreRepository.delete(selection, selectionArgs)
                ITEM_ID ->
                    uri.lastPathSegment?.let {
                        return fakeStoreRepository.delete("${Item::_id.name}=?", arrayOf(it))
                    }
            }
            throw java.lang.IllegalArgumentException("No such uri")
        }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        when(URI_MATCHER.match(uri)) {
            ITEMS -> return fakeStoreRepository.update(values, selection, selectionArgs)
            ITEM_ID ->
                uri.lastPathSegment?.let {
                    return fakeStoreRepository.update(values, "${Item::_id.name}=?", arrayOf(it))
                }
        }
        throw java.lang.IllegalArgumentException("No such uri")
    }
}