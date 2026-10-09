class Solution {
    /**
     * @param {number[]} height
     * @return {number}
     */
    trap(height) {
        let water = 0;
        let l = 0;        
        // find first left wall
        while(height[l] == 0) {
            l++;
        }

        //console.log("first left wall at " + l);

        // walk a "right" pointer forward until it hits an equal or taller wall
        // at which point we have a "well" to "gather" water from
        // then reset and seek the next "well"
        let r = l+1;
        let rMax = height[r];
        let rMaxPos = r;
        while(r < height.length) {
            if(height[r] >= height[l]) {
                //console.log("closing well at right=" + r);
                water += this.gather(height, l, r);
                l = r;
                //console.log("begin seeking new well with left=" + l);
            }
            r++;
        }
        

        let mid = l;
        r = height.length-1;
        l = r-1;
        while(l >= mid) {
            if(height[l] >= height[r]) {
                water += this.gather(height, l, r);
                r = l;
            }
            l--;
        }

        return water;
    }

    gather(height, l, r) {
        //console.log("gathering between " + l + " and " + r);
        var waterTop = Math.min(height[l], height[r]);
        var water = 0;
        for(var i = l+1; i < r; i++) {
            water += waterTop - height[i];
        }

        //console.log("gathered water=" + water);
        return water;
    }
}