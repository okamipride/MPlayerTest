function getLiveUrl(data) {
    var h = data.indexOf('hlsvp') + 8,
    e = data.indexOf('m3u8') + 4,
    result = data.slice(h, e).replace(/\\/g, '');
    return result;
}
