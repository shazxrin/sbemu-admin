@param io.github.shazxrin.sbemuadmin.model.EntityType entityType
@param java.lang.String entityGroupId
@param java.lang.Long entityId
@param io.github.shazxrin.sbemuadmin.model.EntityInfo entityInfo

@template.layout(
content = @`
    <div class="w-full">
        <h2 class="text-3xl font-bold mb-4">${entityInfo.name()}</h2>
        <div class="flex flex-row space-x-8 mb-2">
            <div class="flex flex-col space-y-2">
                <p>Created Date Time</p>
                <p class="text-gray-500 mb-4">
                    ${entityInfo.createdDateTime()}
                </p>
            </div>

            <div class="flex flex-col space-y-2">
                <p>Message Count</p>
                <p class="text-gray-500 mb-4">
                    ${entityInfo.messageCount()}
                </p>
            </div>

            <div class="flex flex-col space-y-2">
                <p>Maximum Deleted Sequence Number</p>
                <p class="text-gray-500 mb-4">
                    ${entityInfo.maximumDeletedSequenceNumber()}
                </p>
            </div>
        </div>


        <form class="flex flex-row space-x-4 mb-12 border-2 border-gray-400 p-4 rounded">
            <div class="flex flex-col space-y-2">
                <label for="startSequenceNumber">Start Sequence Number</label>
                <input
                    class="border border-grey-100 rounded p-2"
                    type="number"
                    id="startSequenceNumber"
                    name="startSequenceNumber"
                    value="0"
                >
            </div>
            <div class="flex flex-col space-y-2">
                <label for="endSequenceNumber">End Sequence Number</label>
                <input
                    class="border border-grey-100 rounded p-2"
                    type="number"
                    id="endSequenceNumber"
                    name="endSequenceNumber"
                    value="100"
                >
            </div>
            <button
                class="border border-grey-100 rounded p-2 place-self-end"
                id="refresh"
                type="button"
            >
                Refresh
            </button>
        </form>

        <div
            hx-get="/message-list/${entityType}/${entityGroupId}/${entityId}"
            hx-include="#startSequenceNumber, #endSequenceNumber"
            hx-swap="innerHTML"
            hx-trigger="load, every 10s, click from:#refresh"
        >
            No messages loaded yet.
        </div>
    </div>
`
)