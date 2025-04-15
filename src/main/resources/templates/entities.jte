@param java.util.List<io.github.shazxrin.sbemuadmin.model.Entity> entities

<ul>
    @if(entities.isEmpty())
        <li>No entities found.</li>
    @endif
    @for(var entity : entities)
        <li>
            <a href="/messages/${entity.type()}/${entity.entityGroupId()}/${entity.entityId()}?startSequenceNumber=0&endSequenceNumber=100">
                ${entity.name()} - ${entity.messageCount()}
            </a>
        </li>
    @endfor
</ul>
