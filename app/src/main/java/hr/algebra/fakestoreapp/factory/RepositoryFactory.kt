package hr.algebra.fakestoreapp.factory

import android.content.Context
import hr.algebra.fakestoreapp.dao.FakeStoreSqlHelper

fun getFakeStoreRepository(context: Context?) = FakeStoreSqlHelper(context)