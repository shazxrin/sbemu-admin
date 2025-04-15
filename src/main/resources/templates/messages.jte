@param java.util.List<io.github.shazxrin.sbemuadmin.model.Message> messages

@if(messages.isEmpty())
<h3>No messages found.</h3>
@else
<ul>
    @for(var message : messages)
        <li>
            ${message.sequenceNumber()} - ${message.body()}
        </li>
    @endfor
</ul>
@endif

