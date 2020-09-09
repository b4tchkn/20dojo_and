package jp.co.cyberagent.dojo2020.data.local

import jp.co.cyberagent.dojo2020.data.local.db.ApplicationDataBase
import jp.co.cyberagent.dojo2020.data.local.db.draft.DraftEntity
import jp.co.cyberagent.dojo2020.data.model.Draft
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface DraftDataSource {
    suspend fun saveDraft(draft: Draft)

    suspend fun fetchAllDraft(): Flow<List<Draft>>

    suspend fun fetchFilteredDraftsByCategory(category: String): Flow<List<Draft>?>

    suspend fun fetchDraftById(id: String): Flow<Draft?>

    suspend fun deleteDraftById(id: String)
}

class DefaultDraftDataSource(private val database: ApplicationDataBase) : DraftDataSource {
    override suspend fun saveDraft(draft: Draft) {
        database.draftDao().insert(draft.toEntityForLocal())
    }

    override suspend fun fetchAllDraft(): Flow<List<Draft>> {
        return database.draftDao().fetchAll().map { entityList ->
            entityList.map { it.toModel() }
        }
    }

    override suspend fun fetchFilteredDraftsByCategory(category: String): Flow<List<Draft>?> {
        return database.draftDao().fetchFilteredByCategory(category).map { entityList ->
            entityList?.map { it.toModel() }
        }
    }

    override suspend fun fetchDraftById(id: String): Flow<Draft?> {
        return database.draftDao().fetch(id).map { it?.toModel() }
    }

    override suspend fun deleteDraftById(id: String) {
        database.draftDao().deleteById(id)
    }

    private fun Draft.toEntityForLocal(): DraftEntity {
        return DraftEntity(id, title, content, startTime, category)
    }

    private fun DraftEntity.toModel(): Draft {
        return Draft(id, title, contents, startTime, category)
    }
}