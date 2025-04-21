@import java.util.List
@import io.github.shazxrin.sbemuadmin.model.Entity

@param List<Entity> entities

<div class="flex flex-col space-y-1">
    @if(entities.isEmpty())
        <span>No entities found.</span>
    @endif
    @for(var entity : entities)
        <a href="/messages/${entity.type()}/${entity.entityGroupId()}/${entity.entityId()}">
            ${entity.name().split(":")[2].replace("|", " > ")}
        </a>
    @endfor
</div>
