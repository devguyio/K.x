<!-- Thank you for opening this PR! 
PR titles and descriptions are improtant for us. We use them to produce commit messages and to better understand the context of the PR.
All the commits in this PR will be squash-merged into a single commit and the commit message will use the PR title and the commit message 
section of this PR description.

Please follow these steps:

1. Set the PR Title
Please use the following format for the PR title. It will be used as the commit message subject (first line in the commit message)

type: some message (type possbile values: fix | feat | chore | docs | refactor | test)

2. Describe your proposed changes which will be used in the commit message body. We've put a sample for you. 
-->

### Proposed Changes

<!-- Commit message body -->

Introduce a request id and a reference to latest request. Dismiss
incoming responses other than from latest request.

Remove timeouts which were used to mitigate the racing issue but are
obsolete now.

Fixes #666

<!-- End of commit body -->

<!-- 3. Why the PR? If no issue exists, describe to us the "why" behind your PR
-->

<!-- 4. Pre-review Checklist.
Go through this checklist and make sure all applicable tasks are checked.
If some of these boxes are not checked, you will be asked to complete these requirements or explain why they do not apply to your PR.
-->

### Pre-review Checklist

- [ ] Write tests
- [ ] Make sure all tests pass and all GitHub checks on this PR are passing.
- [ ] Update documentation for any user-facing impact
- [ ] Set the proper PR title and fill the commit message body section.
