#include <iostream>
using namespace std;

int numbers[20000];
int dp[20000];

int main () {
    int n;
    cin >> n;
    for (int x = 0; x < n; x++) {
        cin >> numbers[x];
    }

    for (int i = 0; i < n; i++) {
        dp[i] = 1;  // we can always do 1 because of itself
        for (int j = 0; j < i; j++) {
            if (numbers[j] < numbers[i]) {  // is able to add itself on
                dp[i] = max(dp[i], (dp[j] + 1));  // take the maximum length for itself
            }
        }
    }

    int longestIncreasingSubsequence = 0;
    for (int x = 0; x < n; x++) {  // take the largest in the dp array
        longestIncreasingSubsequence = max(longestIncreasingSubsequence, dp[x]);
    }

    cout << longestIncreasingSubsequence << "\n";
    return 0;
}
