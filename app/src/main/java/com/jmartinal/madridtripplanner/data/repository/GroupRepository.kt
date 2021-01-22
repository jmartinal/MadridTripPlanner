package com.jmartinal.madridtripplanner.data.repository

import android.util.Log
import com.jmartinal.madridtripplanner.data.datasource.GroupLocalDataSource
import com.jmartinal.madridtripplanner.data.datasource.GroupRemoteDataSource

class GroupRepository(
    private val localDataSource: GroupLocalDataSource,
    private val remoteDataSource: GroupRemoteDataSource
) {
    suspend fun fetchData(accessToken: String) {
        Log.d(TAG, "Fetching Groups data")
        val remoteGroups = remoteDataSource.getGroups(accessToken)
        val localGroups = localDataSource.findAll()
        val deletedGroups = localGroups.filter { !remoteGroups.contains(it) }
        if (deletedGroups.isNotEmpty()) {
            Log.d(
                TAG,
                "${deletedGroups.size} Subgroups to be deleted: ${deletedGroups.map { it.subGroup }}"
            )
            localDataSource.delete(deletedGroups)
        }
        localDataSource.save(remoteGroups)
    }

    companion object {
        private val TAG = GroupRepository::class.simpleName
    }
}