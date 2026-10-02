class Solution {
    /**
     * @param {string} s
     * @return {boolean}
     */
    isValid(s) {
        let open = [];
        for(const c of s) {
            let opener = this.getOpenerFor(c);
            if(opener == null) {
                // not a closer, so therefore a new opener to be pushed
                open.push(c);
            } else if (open.length > 0 && open[open.length-1] == opener) {
                // valid closer resolving last opener
                open.pop();
            } else {
                // invalid closer encountered
                return false;
            }
        }

        return open.length == 0;
    }

    getOpenerFor(c) {
        switch(c) {
            case ']':
                return '[';
            case ')':
                return '(';
            case '}':
                return '{';
            default:
                return null;
        };
    }
}